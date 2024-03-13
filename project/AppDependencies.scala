
import play.core.PlayVersion
import play.sbt.PlayImport.*
import sbt.Keys.libraryDependencies
import sbt.*

object AppDependencies {

  private val bootstrapVersion = "8.5.0"
  private val apiDomainVersion = "0.15.0"
  private val appDomainVersion = "0.41.0"
  private val commonDomainVersion = "0.13.0"

  val compile = Seq(
    "uk.gov.hmrc"    %% "bootstrap-backend-play-30"         % bootstrapVersion,
    "uk.gov.hmrc"    %% "api-platform-application-domain"   % appDomainVersion,
    "uk.gov.hmrc"    %% "api-platform-api-domain"           % apiDomainVersion
  )

  val test = Seq(
    "uk.gov.hmrc"    %% "bootstrap-test-play-30"            % bootstrapVersion,
    "org.mockito"    %% "mockito-scala-scalatest"           % "1.17.30",
    "uk.gov.hmrc"    %% "api-platform-test-common-domain"   % commonDomainVersion
  ).map(_ % Test)

  val it = Seq.empty
}
