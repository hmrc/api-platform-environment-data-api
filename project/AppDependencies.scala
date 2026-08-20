
import play.core.PlayVersion
import play.sbt.PlayImport.*
import sbt.Keys.libraryDependencies
import sbt.*

object AppDependencies {

  private val bootstrapVersion    = "10.8.0"
  private val commonDomainVersion = "1.4.0"
  private val apiDomainVersion    = "1.8.0"
  private val appDomainVersion    = "1.6.0"
  private val mockitoScalaVersion = "2.0.0"

  val compile = Seq(
    "uk.gov.hmrc"    %% "bootstrap-backend-play-30"         % bootstrapVersion,
    "uk.gov.hmrc"    %% "api-platform-common-domain"        % commonDomainVersion,
    "uk.gov.hmrc"    %% "api-platform-application-domain"   % appDomainVersion,
    "uk.gov.hmrc"    %% "api-platform-api-domain"           % apiDomainVersion,
    "uk.gov.hmrc"    %% "internal-auth-client-play-30"      % "4.4.0"
  )

  val test = Seq(
    "uk.gov.hmrc"    %% "bootstrap-test-play-30"                     % bootstrapVersion,
    "org.mockito"    %% "mockito-scala-scalatest"                    % mockitoScalaVersion,
    "uk.gov.hmrc"    %% "api-platform-common-domain-fixtures"        % commonDomainVersion,
    "uk.gov.hmrc"    %% "api-platform-application-domain-fixtures"   % appDomainVersion

  ).map(_ % Test)

  val it = Seq.empty
}
