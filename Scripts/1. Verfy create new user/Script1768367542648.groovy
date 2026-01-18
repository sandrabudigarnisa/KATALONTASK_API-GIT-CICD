import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable

def res = WS.sendRequest(findTestObject('POST Create User'))

WS.verifyResponseStatusCode(res, 201)

def json = new JsonSlurper().parseText(res.getResponseText())
def idVal = json?.id ?: json?.data?.id

assert idVal != null && idVal.toString().trim() != "" : "ID tidak ketemu di response POST"

GlobalVariable.id = idVal.toString()
println("Saved GlobalVariable.id = " + GlobalVariable.id)
