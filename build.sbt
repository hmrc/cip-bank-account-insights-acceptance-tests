import sbt.Test

lazy val testSuite = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin)
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    organization := "uk.gov.hmrc",
    name := "cip-bank-account-insights-acceptance-tests",
    version := "0.1.0",
    scalaVersion := "2.12.15",
    scalacOptions ++= Seq("-feature"),
    console / initialCommands := "import uk.gov.hmrc._",
    libraryDependencies ++= Dependencies.test,
    Test / parallelExecution := false,
    Test / testOptions := Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"),
      Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports/html-report"),
      Tests.Argument("-oD")
    )
  )
