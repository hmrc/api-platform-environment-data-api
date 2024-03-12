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

package uk.gov.hmrc.apiplatformenvironmentdataapi.mocks

import scala.concurrent.Future

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}

import uk.gov.hmrc.apiplatform.modules.applications.core.domain.models.ApplicationResponse
import uk.gov.hmrc.apiplatform.modules.common.domain.models.ClientId
import uk.gov.hmrc.apiplatformenvironmentdataapi.connectors.ThirdPartyApplicationConnector

trait ThirdPartyApplicationConnectorMockModule extends MockitoSugar with ArgumentMatchersSugar {

  val mockThirdPartyApplicationConnector = mock[ThirdPartyApplicationConnector]

  object GetApplicationByClientId {

    def returns(application: ApplicationResponse) =
      when(mockThirdPartyApplicationConnector.getApplicationByClientId(*[ClientId])(*)).thenReturn(Future.successful(Some(application)))

    def returnsNone() =
      when(mockThirdPartyApplicationConnector.getApplicationByClientId(*[ClientId])(*)).thenReturn(Future.successful(None))

    def verifyCalledWith(clientId: ClientId) =
      verify(mockThirdPartyApplicationConnector).getApplicationByClientId(eqTo(clientId))(*)
  }
}
