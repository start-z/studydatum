import xlwt

if __name__ == '__main__':
    book = xlwt.Workbook('utf-8')
    sheet = book.add_sheet(sheetname="测试表", cell_overwrite_ok=True)
    sheet.write(0, 0, '名称')
    sheet.write(0, 1, '图片')
    sheet.write(0, 2, '排名')
    sheet.write(0, 3, '评分')
    sheet.write(0, 4, '作者')
    sheet.write(0, 5, '简介')
    book.save('demo.xlsx')
