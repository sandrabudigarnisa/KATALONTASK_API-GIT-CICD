import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable

def uniqueName = "Sandra_" + System.currentTimeMillis()

def response = WS.sendRequest(findTestObject('POST Create User', [
	('name') : uniqueName
]))

WS.verifyResponseStatusCode(response, 201)

// ambil id dari response dan simpan buat step berikutnya
def json = new groovy.json.JsonSlurper().parseText(response.getResponseText())
def idVal = json?.id ?: json?.data?.id

assert idVal != null : "ID tidak ditemukan di response (cek field id / data.id)"
GlobalVariable.id = idVal.toString()
