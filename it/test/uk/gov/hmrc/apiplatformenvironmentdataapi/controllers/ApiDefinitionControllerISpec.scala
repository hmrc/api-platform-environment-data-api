/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.apiplatformenvironmentdataapi.controllers

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.http.test.WireMockSupport

import uk.gov.hmrc.apiplatformenvironmentdataapi.models._
import uk.gov.hmrc.apiplatformenvironmentdataapi.stubs.ApiDefinitionConnectorStub
import uk.gov.hmrc.apiplatformenvironmentdataapi.utils.{ApiTestData, AsyncHmrcSpec}

class ApiDefinitionControllerISpec extends AsyncHmrcSpec with WireMockSupport with GuiceOneAppPerSuite with ApiDefinitionConnectorStub {

  val stubConfig = Configuration(
    "microservice.services.api-definition.port" -> wireMockPort,
    "microservice.services.internal-auth.port"  -> wireMockPort,
    "metrics.enabled"                           -> false,
    "auditing.enabled"                          -> false
  )

  override def fakeApplication() = GuiceApplicationBuilder()
    .configure(stubConfig)
    .build()

  trait Setup extends ApiTestData {

    val token     = "123456"
    val underTest = app.injector.instanceOf[ApiDefinitionController]
  }

  "fetch" should {

    "return 200 on the agreed route" in new Setup {
      // Authenticate.returns(token)
      FetchApi.returns(anApiDefinition)

      val fakeRequest = FakeRequest("GET", s"/apis?serviceName=$aServiceName").withHeaders("Authorization" -> token)

      val result = route(app, fakeRequest).get
      status(result) mustBe OK
      // the response body is tested in `tests/.../UsersControllerSpec` so not repeated here
    }

    "return 400 if no serviceName" in new Setup {
      // Authenticate.returns(token)
      FetchApi.returns(anApiDefinition)

      val fakeRequest = FakeRequest("GET", s"/apis").withHeaders("Authorization" -> token)

      val result = route(app, fakeRequest).get
      status(result) mustBe BAD_REQUEST
      contentAsJson(result) shouldBe ErrorResponse("BAD_REQUEST", "Missing parameter: serviceName").asJson
    }
  }
}
