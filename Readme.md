# Foundations of Liferay Commerce

This repository contains the Foundations of Liferay Commerce course materials.

## Course Setup Instructions

Liferay's Course Launcher tool automatically prepares your system and sets up a dedicated workspace for course exercises. Use this tool to streamline course environment setup — with **no technical expertise required**.

This tool:

* Checks if Java 21 is installed in your system. If not, it installs Zulu JRE 21 from Azul.
* Downloads and configures the course workspace.
* Initializes the local Liferay DXP bundle.

### Table of Contents

* [Setting Up the Minium Workspace](#setting-up-the-minium-workspace)
* [Manual Setup (Optional)](#manual-setup-optional)

### Setting Up the Minium Workspace

Here, you'll execute the course launcher tool to prepare your system and set up the Minium workspace you'll use in course exercises.

1. Open your terminal and run this command according to your operating system:

   **Linux/Unix**:

   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.github.com/liferay/liferay-enablement-script-library/main/content-manager-course-setup.sh)" -- --foundations-of-commerce linux
   ```

   **Mac**:
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.github.com/liferay/liferay-enablement-script-library/main/content-manager-course-setup.sh)" -- --foundations-of-commerce mac
   ```

   **Windows**:
   ```bash
   powershell Set-ExecutionPolicy Bypass -Scope Process -Force; iex "& { $(irm https://raw.githubusercontent.com/liferay/liferay-enablement-script-library/refs/heads/main/content-manager-course-setup.ps1); install-course --foundations-of-commerce }"
   ```

   This executes the course launcher tool, which automatically checks for and installs Java JDK 21, downloads the course's files, and prepares the Liferay DXP bundle.

> [!NOTE]
> The full process may take a few minutes to complete.

2. Once the "Liferay bundle initialized" message displays, verify the `liferay-course-foundations-of-commerce/` folder was created.

1. Go to the workspace's root folder in your terminal:

   ```bash
   cd liferay-course-foundations-of-commerce/
   ```

1. Run this command to start the Liferay server:

   **Unix-based**:

   ```bash
   ./bundles/tomcat/bin/startup.sh
   ```

   **Windows**:

   ```bash
   .\bundles\tomcat\bin\startup.bat
   ```

1. Verify the “Tomcat started“ message displays.

   This indicates that the server has initiated its startup process in the background.

1. Access your Liferay DXP instance by going to [localhost:8080](http://localhost:8080) in your browser.

> [!NOTE]
> Server startup may take a few minutes to complete.

7. Sign in using these credentials:

   * Username: `admin@minium.demo`
   * Password: `learn`

1. Open the *Global Menu*, go to the *Control Panel* tab, and click *Search*.

1. Go to the *Index Actions* tab and click *Reindex for All Search Indexes*.

1. When prompted, click *Execute* to confirm.

1. Take some time to explore the site and resources included in the training workspace.

> [!NOTE]
> To shutdown your Liferay server, run this command in your terminal:
>
> **Unix-based**:
>
> ```bash
> ./bundles/tomcat/bin/shutdown.sh
> ```
>
> **Windows**:
>
> ```bash
> .\bundles\tomcat\bin\shutdown.bat
> ```

Great! With your environment set up, you’re ready to start exploring Minium's digital commerce solution.

### Manual Setup (Optional)

Alternatively, you can set up your course environment manually.

> [!NOTE]
> This process involves more technical steps. If you're using a company system, you may need to contact your company's IT support.

1. Ensure your system satisfies the following prerequisites:

   * Git ([macOS](https://git-scm.com/download/mac) | [Windows](https://git-scm.com/download/win) | [Linux/Unix](https://git-scm.com/download/linux))
   * Java JDK 21 ([macOS](https://learn.microsoft.com/en-us/java/openjdk/install#install-on-macos) | [Windows](https://learn.microsoft.com/en-us/java/openjdk/install#install-on-windows) | [Linux](https://learn.microsoft.com/en-us/java/openjdk/install#install-on-ubuntu))

1. Open your terminal and clone the training workspace to your computer:

   ```bash
   git clone https://github.com/liferay/liferay-course-foundations-of-commerce
   ```

   This saves a copy of the project in your current terminal directory.

> [!NOTE]
> If you've cloned the repo previously, ensure your workspace is up to date by running `git pull origin main`.

3. Go to the workspace's root folder in your terminal:

   ```bash
   cd liferay-course-foundations-of-commerce
   ```

1. Initialize your Liferay bundle.

   **Unix-based**:

   ```bash
   ./gradlew initBundle
   ```

   **Windows**:

   ```bash
   .\gradlew.bat initBundle
   ```

   This downloads and builds dependencies for running Liferay, including the Liferay Tomcat server.

1. Follow steps 3-9 of the [previous section](#setting-up-the-minium-workspace).