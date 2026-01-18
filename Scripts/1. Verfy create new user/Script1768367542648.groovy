import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.logInfo("RAW baseUrl = [" + GlobalVariable.baseUrl + "]")
KeywordUtil.logInfo("LENGTH baseUrl = " + (GlobalVariable.baseUrl == null ? "null" : GlobalVariable.baseUrl.toString().length()))

def b = GlobalVariable.baseUrl
b = (b == null) ? "" : b.toString().trim()

b = b.replaceAll(/^'+|'+$/, "")
b = b.replaceAll(/^\"+|\"+$/, "")

GlobalVariable.baseUrl = b
KeywordUtil.logInfo("SANITIZED baseUrl = [" + GlobalVariable.baseUrl + "]")

assert GlobalVariable.baseUrl != null && GlobalVariable.baseUrl.toString().trim() != "" : "baseUrl kosong setelah sanitize!"

def res = WS.sendRequest(findTestObject('POST Create User'))

KeywordUtil.logInfo("POST status = " + res.getStatusCode())
KeywordUtil.logInfo("POST body = " + res.getResponseText())

WS.verifyResponseStatusCode(res, 201)

//ambil ID
def json = new JsonSlurper().parseText(res.getResponseText())
def idVal = json?.id ?: json?.data?.id

assert idVal != null && idVal.toString().trim() != "" : "ID tidak ketemu di response POST. Body=" + res.getResponseText()

GlobalVariable.id = idVal.toString().trim()
KeywordUtil.logInfo("Saved GlobalVariable.id = " + GlobalVariable.id)
