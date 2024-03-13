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

package uk.gov.hmrc.apiplatformenvironmentdataapi.services

import uk.gov.hmrc.http.HeaderCarrier

import uk.gov.hmrc.apiplatformenvironmentdataapi.mocks.ApiDefinitionConnectorMockModule
import uk.gov.hmrc.apiplatformenvironmentdataapi.utils.{AsyncHmrcSpec, _}

class ApisServiceSpec extends AsyncHmrcSpec with ApiTestData {

  trait Setup extends ApiDefinitionConnectorMockModule {
    implicit val hc: HeaderCarrier = HeaderCarrier()

    val underTest = new ApisService(mockApiDefinitionConnector)
  }

  "fetchApi" should {
    "return an aou with the requested serviceName" in new Setup {
      FetchApi.returns(anApiDefinition)

      val result = await(underTest.fetchApi(aServiceName))

      result shouldBe Some(anApiDefinition)
      FetchApi.verifyCalledWith(aServiceName)
    }

    "return None if the api was not found" in new Setup {
      FetchApi.returnsNone()

      val result = await(underTest.fetchApi(aServiceName))

      result shouldBe None
      FetchApi.verifyCalledWith(aServiceName)
    }
  }
}
