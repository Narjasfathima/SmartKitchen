from django.shortcuts import render
from Order_Manage.models import OrderProductTb
from Order_Manage.models import BookProductTb
from User.models import UserTb

from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Order_Manage.serializer import OrderProduct_serializer, BookProduct_serializer
import datetime
from Product.models import ProductTb
from django.http import JsonResponse
from django.db import connection


# Create your views here.
def view_order(request):
    obj = connection.cursor()
    obj.execute('SELECT book_product_tb.*,order_product_tb.*,user_tb.*,product_tb.* FROM book_product_tb INNER JOIN order_product_tb ON order_product_tb.oid=book_product_tb.oid_id INNER JOIN user_tb ON user_tb.lid_id=book_product_tb.uid_id INNER JOIN product_tb ON product_tb.pid=order_product_tb.pid_id WHERE book_product_tb.bstatus="not delivered"')
    context = {
        'objval':obj.fetchall(),
    }
    return render(request,'Order_Manage/View_order.html',context)

def confirm_order(request,book_id):
    obj=BookProductTb.objects.get(bid=book_id)
    obj.bstatus='delivered'
    obj.save()
    return view_order(request)

def cancel_order(request,book_id):
    obj=BookProductTb.objects.get(bid=book_id)
    obj.bstatus='cancel'
    obj.save()
    return view_order(request)

class Book_view(APIView):
    def get(self,request):
        obj=BookProductTb.objects.all()
        ser=BookProduct_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        p = request.data['pid']
        o = ProductTb.objects.get(pid=p)
        pp = o.price
        q = request.data['oqty']
        print(pp)
        print(q)
        t=pp*q
        print(t)

        ob = OrderProductTb()
        ob.oqty=request.data['oqty']
        ob.oprice = t
        ob.pid_id = request.data['pid']
        ob.save()


        obj=BookProductTb()
        obj.sid_id=request.data['sid']
        obj.uid_id=request.data['uid']
        obj.oid_id= ob.oid
        e = datetime.datetime.now()
        obj.bdate = e.date()
        obj.btime = e.time()
        obj.bstatus = 'cart'
        obj.save()
        return HttpResponse("OK")

class Order_view(APIView):
    def get(self,request):
        obj=OrderProductTb.objects.all()
        ser=OrderProduct_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=OrderProductTb()
        obj.pid_id=request.data['pid']
        obj.oqty=request.data['oqty']
        obj.oprice=request.data['oprice']
        obj.save()
        return HttpResponse("OK")




class Book_view1(APIView):
    def get(self,request):
        cursor = connection.cursor()
        cursor.execute(
            "SELECT order_product_tb.oqty, order_product_tb.oprice, product_tb.pname,product_tb.pqty, product_tb.ptype, product_tb.price, book_product_tb.bdate, book_product_tb.uid_id, book_product_tb.bstatus, book_product_tb.bid FROM   order_product_tb INNER JOIN  product_tb ON order_product_tb.pid_id = product_tb.pid INNER JOIN book_product_tb ON order_product_tb.oid = book_product_tb.oid_id")
        allv = cursor.fetchall()
        json_data = []
        for obj in allv:
            json_data.append(
                {"oqty": obj[0], "oprice": obj[1], "pname": obj[2],"pqty":obj[3],"ptype":obj[4],"price":obj[5],"bdate":obj[6],"uid":obj[7],"bstatus":obj[8],"bid":obj[9],})
        return JsonResponse(json_data, safe=False)

    def post(self,request):
        uid=request.data['uid']
        obj=BookProductTb.objects.filter(uid_id=uid).update(bstatus='not delivered')
        print(obj.count())
        return HttpResponse("OK")