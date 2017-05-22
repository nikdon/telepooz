import de.heikoseeberger.sbtheader.license.Apache2_0

headers := Map("scala" -> Apache2_0("2016", "Nikolay Donets"))

lazy val buildSettings = Seq(
  organization := "com.github.nikdon",
  name := "telepooz",
  version := "0.4.2",
  scalaVersion := "2.12.2",
  licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
)

lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-unchecked",
//  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture"
)

lazy val baseSettings = Seq(
  scalacOptions ++= compilerOptions ++ Seq(
    "-Ywarn-unused-import"
  ),
  testOptions in Test += Tests.Argument("-oF"),
  scalacOptions in (Compile, console) := compilerOptions,
  scalacOptions in (Compile, test) := compilerOptions,
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  ),
  mappings.in(Compile, packageBin) += baseDirectory.in(ThisBuild).value / "LICENSE" -> "LICENSE"
)

buildSettings ++ baseSettings

lazy val akkaVersion      = "2.5.1"
lazy val akkaHttpV        = "10.0.6"
lazy val akkaHttpJsonV    = "1.16.1"
lazy val catsV            = "0.9.0"
lazy val circeVersion     = "0.8.0"
lazy val shapelessVersion = "2.3.2"

lazy val scalaTestV  = "3.0.3"
lazy val scalaCheckV = "1.13.5"

libraryDependencies ++= Seq(
  "org.typelevel"     %% "cats"                 % catsV,
  "com.typesafe.akka" %% "akka-actor"           % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit"         % akkaVersion,
  "com.typesafe.akka" %% "akka-http-core"       % akkaHttpV,
  "com.typesafe.akka" %% "akka-http"            % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpV,
  "io.circe"          %% "circe-core"           % circeVersion,
  "io.circe"          %% "circe-generic"        % circeVersion,
  "io.circe"          %% "circe-generic-extras" % circeVersion,
  "io.circe"          %% "circe-parser"         % circeVersion,
  "io.circe"          %% "circe-testing"        % circeVersion,
  "de.heikoseeberger" %% "akka-http-circe"      % akkaHttpJsonV,
  "com.chuusai"       %% "shapeless"            % shapelessVersion,
  "org.scalatest"     %% "scalatest"            % scalaTestV % "test",
  "org.scalacheck"    %% "scalacheck"           % scalaCheckV % "test"
)
