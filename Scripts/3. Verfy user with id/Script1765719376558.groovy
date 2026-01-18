import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

assert GlobalVariable.id != null && GlobalVariable.id.trim() != "" : "GlobalVariable.id kosong. Pastikan TC 1 jalan & berhasil simpan id"

WS.sendRequest(findTestObject('GET User By Id', [('id') : GlobalVariable.id]))
