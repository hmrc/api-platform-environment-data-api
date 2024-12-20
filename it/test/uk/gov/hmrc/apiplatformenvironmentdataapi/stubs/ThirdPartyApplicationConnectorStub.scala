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

package uk.gov.hmrc.apiplatformenvironmentdataapi.stubs

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.stubbing.StubMapping

import play.api.test.Helpers._

import uk.gov.hmrc.apiplatform.modules.common.domain.models.ClientId
import uk.gov.hmrc.apiplatformenvironmentdataapi.utils.WireMockExtensions

trait ThirdPartyApplicationConnectorStub extends WireMockExtensions {

  object GetApplicationByClientId {

    def stubWithClientId(clientId: ClientId): StubMapping =
      stubFor(
        get(urlPathEqualTo(s"/application"))
          .withQueryParam("clientId", equalTo(clientId.value))
          .willReturn(
            aResponse()
              .withStatus(OK)
              .withBody(applicationResponseBody)
          )
      )
  }

  private val applicationResponseBody =
    s"""{
       |  "id": "967226ae-46ca-4b71-a76c-72efbc402a9b",
       |  "clientId": "tl68AJH3PA8kKe7H9gxIatou2UTK",
       |  "gatewayId": "gateway-id",
       |  "name": "Application Name",
       |  "deployedTo": "PRODUCTION",
       |  "collaborators": [],
       |  "createdOn": "2023-11-22T14:56:57.833Z",
       |  "grantLength": 30,
       |  "redirectUris": [],
       |  "access": {
       |    "redirectUris": [],
       |    "overrides": [],
       |    "accessType": "STANDARD"
       |  },
       |  "state": {
       |    "name": "TESTING",
       |    "updatedOn": "2023-11-22T14:56:57.833Z"
       |  },
       |  "rateLimitTier": "BRONZE",
       |  "blocked": false,
       |  "trusted": false,
       |  "ipAllowlist": {
       |    "required": false,
       |    "allowlist": []
       |  },
       |  "moreApplication": {
       |    "allowAutoDelete": false,
       |    "lastActionActor": "UNKNOWN"
       |  }
       |}""".stripMargin
}
