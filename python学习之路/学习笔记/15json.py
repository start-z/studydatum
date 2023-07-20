import json

obj = {"age": 20, "name": "小周"}
#转换为json
print(json.dumps(obj))
#解析json
objJsonTo = json.loads(json.dumps(obj))
print(objJsonTo)
