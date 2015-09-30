package automationFramework;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private ExtentReports extent;
	public ExtentReports setExtentReports(String browser) {
		extent = new ExtentReports("report-"+browser+".html", true);
		// optional
		extent.config()
			.documentTitle("Automation Report - BVT")
			.reportName("Build Verification Test")
			.reportHeadline("");
		
		// optional
		extent
			.addSystemInfo("Selenium Version", "2.47")
			.addSystemInfo("Environment", "QA")
			.addSystemInfo("Browser", browser);
		
		return extent;
	}
}