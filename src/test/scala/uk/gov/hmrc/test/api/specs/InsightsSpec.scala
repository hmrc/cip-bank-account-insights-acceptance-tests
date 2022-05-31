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

package uk.gov.hmrc.test.api.specs

import org.assertj.core.api.Assertions.assertThat
import uk.gov.hmrc.insights.model.response.response_codes.ACCOUNT_NOT_ON_WATCH_LIST
import uk.gov.hmrc.test.api.testdata.BankAccounts.UNKNOWN_ACCOUNT

class InsightsSpec extends BaseSpec {

  Feature("Check the insight API") {

    Scenario("Get risking information for a bank account") {
      Given("I want to see if we hold any risking information for a bank account")

      When("I use the check insights API to see what information we hold")

      val actual = accountCheckHelper.getAccountCheckResponse(UNKNOWN_ACCOUNT)

      Then("I am given the relevant risking information")

      assertThat(actual.riskScore).isEqualTo(0)
      assertThat(actual.reason).isEqualTo(ACCOUNT_NOT_ON_WATCH_LIST)
    }
  }
}
