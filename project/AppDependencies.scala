
import play.core.PlayVersion
import play.sbt.PlayImport.*
import sbt.Keys.libraryDependencies
import sbt.*

object AppDependencies {

  private val bootstrapVersion = "9.7.0"
  private val apiDomainVersion = "0.19.1"
  private val appDomainVersion = "0.67.0"

  val compile = Seq(
    "uk.gov.hmrc"    %% "bootstrap-backend-play-30"         % bootstrapVersion,
    "uk.gov.hmrc"    %% "api-platform-application-domain"   % appDomainVersion,
    "uk.gov.hmrc"    %% "api-platform-api-domain"           % apiDomainVersion,
    "uk.gov.hmrc"    %% "internal-auth-client-play-30"      % "3.0.0"
  )

  val test = Seq(
    "uk.gov.hmrc"    %% "bootstrap-test-play-30"                     % bootstrapVersion,
    "org.mockito"    %% "mockito-scala-scalatest"                    % "1.17.30",
    "uk.gov.hmrc"    %% "api-platform-application-domain-fixtures"   % appDomainVersion
    
  ).map(_ % Test)

  val it = Seq.empty
}
