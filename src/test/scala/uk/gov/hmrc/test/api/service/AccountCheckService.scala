/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.test.api.service

import play.api.libs.json.Json
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.insights.model.request.BankAccountInsightsRequest
import uk.gov.hmrc.insights.model.request.BankAccountInsightsRequest.implicits.bankAccountInsightsRequestWrites
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration
import uk.gov.hmrc.test.api.helpers.Endpoints

import scala.concurrent.Await
import scala.concurrent.duration._

class AccountCheckService extends HttpClient {
  val host: String            = TestConfiguration.url("cip-bank-account-insights")
  val checkAccountURL: String = s"$host/${Endpoints.CHECK_INSIGHTS}"

  def postInsightsCheck(accountDetails: BankAccountInsightsRequest): StandaloneWSRequest#Self#Response =
    Await.result(
      post(
        checkAccountURL,
        Json.toJsObject(accountDetails).toString(),
        ("Content-Type", "application/json")
      ),
      10.seconds
    )
}
