
from mitmproxy import ctx, http

class demo():
    def __init__(self):
        self.num = 0

    def request(self, flow: http.HTTPFlow):
        if flow.request.host != 'www.baidu.com':
            return
        ctx.log.info("有人正在搜素" + flow.request.query.get("wd"))
        flow.request.query.set_all("123")

addons = {demo()}
