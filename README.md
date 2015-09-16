## Sample Selenium Test Uing Grid
##### Author: Scott Yoon
##### Email: scottyoon221@yahoo.com

### Intro

Selenium is a popular web browser automation testing suite. The current project is to demonstrate the use of Selenium driver along with other supporting frameworks. Following lists summary of each component that were used to make the web automation test into realization for the project:
- **Selenium Driver 2.0:** API for controlling element of web page
- **TestNG:** Framework that supports Selenium to verfiy results and create flexible test configuration
- **Selenium-Grid:**  Server that distribute tests to multiple environment in parallel
- **Extent Reports:** API to generate HTML report for Selenium test

### Workflow
1. Selnium hub makes handshakes with nodes.
2. Execute testNG XML file to run tests.
3. Selnium hub receives tests then distribute it to appropriate nodes.
4. Nodes receives tests then executes tests.
5. Once tests are completed, report file is generated.

### Version of Development Platform, Tools and Libraries:
- **OS:** Mac 10.10.5
- **IDE:** Eclipse Kepler SR2 Java IDE (4.4.2)
- **Java:** jdk 1.8.0_60
- **Unit Test Framework:** TestNG (6.9.5)
- **Selenium Driver:** selenium-2.47.1
- **Extent Report:** extentreports-java-2.04.jar

### Test Distributer (Hub)
- **OS:** Mac 10.10.5
- **Selenium-Grid:** selenium-server-standalone-2.47.1.jar

### System Under Test (Node)
- **OS:** Mac 10.10.5
- **Browser1:** chrome (Version 45.0.2454.85 (64-bit)) with chromedriver_mac32 (v.2.19)
- **Browser2:** firefox (Version 40.0.3)


### Server and  Selenium Commands in Terminal

##### Type following command in Mac terminal to launch selenium grid host server on your server machine (hub):
```bash
java -jar selenium-server-standalone-2.47.1.jar -role hub
```
##### Type following command in Mac terminal to launch selenium grid on your client machine (node):
```bash
java -jar selenium-server-standalone-2.47.1.jar -role node  -hub http://<hub-ip>:4444/grid/register
```
##### To allow only specific browser in a node, type following command instead:
```bash
java -jar selenium-server-standalone-2.47.1.jar -role node -browser browserName=chrome -browser browserName=chrome -browser browserName=firefox -hub http://<hub-ip>:4444/grid/register
```
##### Type following command in Mac terminal to execute test:
```bash
java -cp "/Project_library_Path/libs/*:/Project_bin_path/bin" org.testng.TestNG suite.xml
```
### Summary of Configuration and Test Files

##### Run Browser test in Parallel
Modify suite.xml file under root directory to configure the type of os, browser, version, test and whether the tests need to be executed in parallel.

##### Test Case
Modify TestCase.java file under src/automationFramework folder to change test cases.

##### Report Configuration
Modify ExtentManager.java file under src/automationFramework folder to configure properties that needs to show
up in the report.

##### Report File
Once the test is completed, it will generate report under root directory as report-%browserName%.html.

#### Reference

- **Selenium:** http://www.seleniumhq.org/
- **TestNg:** http://testng.org/doc/documentation-main.html
- **Extent Report:** http://extentreports.relevantcodes.com/


#### Troubleshootings
- Some Eclipse IDE has problem installing TestNG plugin. Use the version of Eclipse and Java mentioned in **<Development platform, tools and libraries>**
- Chrome requires chromedriver to work properly with Selenium.


