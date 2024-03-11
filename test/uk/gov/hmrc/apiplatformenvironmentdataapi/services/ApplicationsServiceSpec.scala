/*
 * Copyright 2023 HM Revenue & Customs
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

import scala.concurrent.ExecutionContext.Implicits.global

import uk.gov.hmrc.http.HeaderCarrier

import uk.gov.hmrc.apiplatformenvironmentdataapi.mocks.ThirdPartyApplicationConnectorMockModule
import uk.gov.hmrc.apiplatformenvironmentdataapi.utils.{ApplicationTestData, AsyncHmrcSpec}

class ApplicationsServiceSpec extends AsyncHmrcSpec with ApplicationTestData {

  trait Setup extends ThirdPartyApplicationConnectorMockModule {
    implicit val hc: HeaderCarrier = HeaderCarrier()

    val underTest = new ApplicationsService(mockThirdPartyApplicationConnector)

  }

  "getApplicationByClientId" should {
    "return an application with the requested clientId" in new Setup {
      GetApplicationByClientId.returns(applicationResponse)

      val result = await(underTest.getApplicationByClientId(clientId))

      result shouldBe Some(application)
      GetApplicationByClientId.verifyCalledWith(clientId)
    }

    "return None if the application was not found" in new Setup {
      GetApplicationByClientId.returnsNone()

      val result = await(underTest.getApplicationByClientId(clientId))

      result shouldBe None
      GetApplicationByClientId.verifyCalledWith(clientId)
    }
  }
}
