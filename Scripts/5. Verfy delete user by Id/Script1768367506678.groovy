import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

assert GlobalVariable.id != null && GlobalVariable.id.toString().trim() != '' :
    "GlobalVariable.id kosong. Pastikan 'Verify create new user' jalan dulu."

def res = WS.sendRequest(findTestObject('DELETE User', [
    ('id'): GlobalVariable.id.toString()
]))

def sc = res.getStatusCode()
assert (sc == 200 || sc == 204) : "Delete gagal. Status code=" + sc + " body=" + res.getResponseText()

KeywordUtil.logInfo("DELETE user sukses. id=" + GlobalVariable.id + " status=" + sc)