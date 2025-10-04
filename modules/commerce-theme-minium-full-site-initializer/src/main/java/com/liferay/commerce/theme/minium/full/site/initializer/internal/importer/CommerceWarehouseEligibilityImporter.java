package com.liferay.commerce.theme.minium.full.site.initializer.internal.importer;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountGroup;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountGroupLocalService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseRelService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jeff Handa
 */
@Component(service = CommerceWarehouseEligibilityImporter.class)

public class CommerceWarehouseEligibilityImporter {

    public void importCommerceWarehouseEligibility(
            JSONArray jsonArray, long scopeGroupId, long userId)
            throws PortalException {

        User user = _userLocalService.getUser(userId);

        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setCompanyId(user.getCompanyId());
        serviceContext.setScopeGroupId(scopeGroupId);
        serviceContext.setUserId(userId);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String scope = jsonObject.getString("scope");

            if (scope.equals("account")) {
                _importAccountCommerceWarehouseEligibility(jsonObject, serviceContext);
            }
            else if (scope.equals("account-group")){
                _importAccountGroupCommerceWarehouseEligibility(jsonObject, serviceContext);
            }else {
                _log.debug(scope + " is not supported");
            }
        }
    }

    private void _importAccountCommerceWarehouseEligibility(
            JSONObject jsonObject, ServiceContext serviceContext)
            throws PortalException {

        JSONArray accountExternalReferenceCodes = jsonObject.getJSONArray("accountExternalReferenceCodes");
        String warehouseExternalReferenceCode = jsonObject.getString("warehouseExternalReferenceCode");

        long warehouseId = _commerceInventoryWarehouseLocalService.
                getCommerceInventoryWarehouseByExternalReferenceCode(
                        warehouseExternalReferenceCode, serviceContext.getCompanyId()).getCommerceInventoryWarehouseId();

        for (int i = 0; i < accountExternalReferenceCodes.length(); i++) {

            String accountExternalReferenceCode = accountExternalReferenceCodes.getString(i);

            long accountId = _accountEntryLocalService.
                    getAccountEntryByExternalReferenceCode(
                            accountExternalReferenceCode, serviceContext.getCompanyId()).getAccountEntryId();

            _commerceInventoryWarehouseRelService.
                    addCommerceInventoryWarehouseRel(
                            AccountEntry.class.getName(), accountId, warehouseId);
        }
    }

    private void _importAccountGroupCommerceWarehouseEligibility(
            JSONObject jsonObject, ServiceContext serviceContext)
            throws PortalException {

        JSONArray accountGroupExternalReferenceCodes = jsonObject.getJSONArray("accountGroupExternalReferenceCodes");
        String warehouseExternalReferenceCode = jsonObject.getString("warehouseExternalReferenceCode");

        long warehouseId = _commerceInventoryWarehouseLocalService.
                getCommerceInventoryWarehouseByExternalReferenceCode(
                        warehouseExternalReferenceCode, serviceContext.getCompanyId()).getCommerceInventoryWarehouseId();

        for (int i = 0; i < accountGroupExternalReferenceCodes.length(); i++) {

            String accountGroupExternalReferenceCode = accountGroupExternalReferenceCodes.getString(i);

            long accountGroupId = _accountGroupLocalService.getAccountGroupByExternalReferenceCode(
                    accountGroupExternalReferenceCode, serviceContext.getCompanyId()).getAccountGroupId();

            _commerceInventoryWarehouseRelService.
                    addCommerceInventoryWarehouseRel(
                            AccountGroup.class.getName(), accountGroupId, warehouseId);
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
            CommerceWarehouseEligibilityImporter.class);

    @Reference
    private AccountEntryLocalService _accountEntryLocalService;

    @Reference
    private AccountGroupLocalService _accountGroupLocalService;

    @Reference
    private CommerceInventoryWarehouseLocalService _commerceInventoryWarehouseLocalService;

    @Reference
    private CommerceInventoryWarehouseRelService _commerceInventoryWarehouseRelService;

    @Reference
    private UserLocalService _userLocalService;
}