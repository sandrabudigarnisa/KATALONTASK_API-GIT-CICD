def uniqueName = "Sandra_" + System.currentTimeMillis()

KeywordUtil.logInfo("PROFILE baseUrl = " + GlobalVariable.baseUrl)
KeywordUtil.logInfo("Create user uniqueName = " + uniqueName)

def response = WS.sendRequest(findTestObject('POST Create User', [
  ('name') : uniqueName
]))

KeywordUtil.logInfo("StatusCode = " + response.getStatusCode())
KeywordUtil.logInfo("ResponseBody = " + response.getResponseText())

WS.verifyResponseStatusCode(response, 201)

// ambil id
def json = new groovy.json.JsonSlurper().parseText(response.getResponseText())
def idVal = json?.id ?: json?.data?.id
assert idVal != null : "ID tidak ditemukan di response (cek field id / data.id)"
GlobalVariable.id = idVal.toString()
