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

package uk.gov.hmrc.apiplatformenvironmentdataapi.connectors

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

import uk.gov.hmrc.http.HttpReads.Implicits._
import uk.gov.hmrc.http.client.HttpClientV2
import uk.gov.hmrc.http.{HeaderCarrier, StringContextOps}

import uk.gov.hmrc.apiplatform.modules.apis.domain.models._

@Singleton
class ApiDefinitionConnector @Inject() (http: HttpClientV2, config: ApiDefinitionConnector.Config)(implicit ec: ExecutionContext) {

  def fetchApi(serviceName: ServiceName)(implicit hc: HeaderCarrier): Future[Option[ApiDefinition]] = {
    http.get(url"${config.serviceBaseUrl}/api-definition/$serviceName").execute[Option[ApiDefinition]]
  }
}

object ApiDefinitionConnector {

  case class Config(
      serviceBaseUrl: String
    )
}
