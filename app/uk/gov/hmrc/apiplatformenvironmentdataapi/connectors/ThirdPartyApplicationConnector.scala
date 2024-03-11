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

package uk.gov.hmrc.apiplatformenvironmentdataapi.connectors

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

import uk.gov.hmrc.http.HttpReads.Implicits._
import uk.gov.hmrc.http.{HeaderCarrier, HttpClient}

import uk.gov.hmrc.apiplatform.modules.applications.core.domain.models.ApplicationResponse
import uk.gov.hmrc.apiplatform.modules.common.domain.models.ClientId

@Singleton
class ThirdPartyApplicationConnector @Inject() (http: HttpClient, config: ThirdPartyApplicationConnector.Config)(implicit ec: ExecutionContext) {

  def getApplicationByClientId(clientId: ClientId)(implicit hc: HeaderCarrier): Future[Option[ApplicationResponse]] = {
    http.GET[Option[ApplicationResponse]](url = s"${config.serviceBaseUrl}/application", queryParams = Seq("clientId" -> clientId.value))
  }
}

object ThirdPartyApplicationConnector {
  case class Config(serviceBaseUrl: String)
}
