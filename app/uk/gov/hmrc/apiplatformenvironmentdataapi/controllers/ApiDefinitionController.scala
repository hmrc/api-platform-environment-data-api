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

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

import play.api.mvc.{Action, AnyContent, ControllerComponents}
import uk.gov.hmrc.internalauth.client._
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import uk.gov.hmrc.apiplatform.modules.apis.domain.models._
import uk.gov.hmrc.apiplatformenvironmentdataapi.models.{ApiResponse, ErrorResponse}
import uk.gov.hmrc.apiplatformenvironmentdataapi.services.ApisService

@Singleton()
class ApiDefinitionController @Inject() (apisService: ApisService, cc: ControllerComponents, auth: BackendAuthComponents)(implicit ec: ExecutionContext)
    extends BackendController(cc) {
  private lazy val notFound = NotFound(ErrorResponse("NOT_FOUND", "API could not be found").asJson)

  def fetch(serviceName: ServiceName): Action[AnyContent] =
    auth.authorizedAction(predicate = Predicate.Permission(Resource.from("api-platform-environment-data-api", "api-definitions/all"), IAAction("READ"))).async {
      implicit request: AuthenticatedRequest[AnyContent, Unit] =>
        apisService.fetchApi(serviceName) map {
          case Some(apiDef) => Ok(ApiResponse.from(apiDef).asJson)
          case _            => notFound
        } recover recovery
    }
}
