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

package uk.gov.hmrc.apiplatformenvironmentdataapi.models

import play.api.libs.json.{Format, JsValue, Json, OFormat}

import uk.gov.hmrc.apiplatform.modules.applications.core.domain.models.{ApplicationName, ApplicationWithCollaborators}
import uk.gov.hmrc.apiplatform.modules.common.domain.models.{ApplicationId, Environment}

case class Applications(value: List[Application]) extends AnyVal {
  def asJson: JsValue = Applications.format.writes(this)
}

object Applications {
  implicit val format: Format[Applications] = Json.valueFormat[Applications]
}

case class Application(applicationId: ApplicationId, name: ApplicationName, environment: Environment) {
  def asJson: JsValue = Application.format.writes(this)
}

object Application {

  def from(appWithCollaborators: ApplicationWithCollaborators) = Application(
    applicationId = appWithCollaborators.id,
    name = appWithCollaborators.name,
    environment = appWithCollaborators.deployedTo
  )

  implicit val format: OFormat[Application] = Json.format[Application]
}
