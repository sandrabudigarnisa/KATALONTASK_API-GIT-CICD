import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

def uniqueName = "Sandra_" + System.currentTimeMillis()

WS.sendRequest(findTestObject('POST Create User', [
	('name')  : uniqueName,
	('job')   : 'QA Tester New',
	('salary'): 10000000
]))
