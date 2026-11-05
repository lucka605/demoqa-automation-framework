# Test Cases

| ID | Area | Scenario | Automation | Expected result |
|---|---|---|---|---|
| UI-001 | Text Box | Valid form data | `TextBoxTests.validSubmission` | All supplied values appear in output |
| UI-002 | Text Box | Invalid email | `TextBoxTests.invalidEmailIsRejected` | Email field marked invalid |
| UI-003 | Check Box | Select Home | `CheckBoxTests.homeSelectionDisplaysResult` | Selected node reported |
| UI-004 | Radio Button | Select Yes | `RadioButtonTests.yesRadio` | Yes label displayed |
| UI-005 | Radio Button | Select Impressive | `RadioButtonTests.impressiveRadio` | Impressive displayed |
| UI-006 | Buttons | Double click | `ButtonsTests.doubleClickMessage` | Confirmation appears |
| UI-007 | Buttons | Right click | `ButtonsTests.rightClickMessage` | Confirmation appears |
| UI-008 | Alerts | Accept alert | `AlertsTests.acceptSimpleAlert` | Alert text received |
| UI-009 | Alerts | Cancel confirm | `AlertsTests.dismissConfirmation` | Cancel result displayed |
| UI-010 | Tables | Search known fixture | `WebTablesTests.searchExistingEmployee` | Search result contains fixture |
| UI-011 | Forms | Submit valid basic student | `PracticeFormTests.submitMinimumValidStudent` | Confirmation modal appears |
| API-001 | Books | GET catalog | `BookStoreApiTests.getBooksCatalog` | 200, books array nonempty |
| API-002 | Books | GET book by ISBN | `BookStoreApiTests.getBookByIsbn` | 200 and matching ISBN |
| API-003 | Account | Invalid login | `BookStoreApiTests.invalidCredentialsNotAuthorized` | 200 with false, according to demo contract |

## Notes
These are test specifications; not evidence of test execution. Log actual results only after running Maven.
