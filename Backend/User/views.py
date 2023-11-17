from django.shortcuts import render
from User.models import UserTb
from Home.models import LoginTb

from django.http import HttpResponse, Http404
from rest_framework.response import Response
from rest_framework.views import APIView
from User.serializer import User_serializer

class User_view(APIView):
    def get(self,request):
        obj=UserTb.objects.all()
        ser=User_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        ob=LoginTb()
        ob.username=request.data['username']
        ob.password=request.data['password']
        ob.ltype='user'
        ob.save()
        obj=UserTb()
        obj.lid_id=ob.lid
        obj.uname=request.data['uname']
        obj.ugender=request.data['ugender']
        obj.uplace=request.data['uplace']
        obj.homename=request.data['homename']
        obj.upost=request.data['upost']
        obj.upin=request.data['upin']
        obj.uemail=request.data['uemail']
        obj.uphno=request.data['uphno']
        obj.save()
        ser = User_serializer(obj, many=True)
        return Response(ser.data)

from django.http import JsonResponse
from django.db import connection

class User_view1(APIView):
    def get(self,request):
        cursor = connection.cursor()
        cursor.execute("SELECT user_tb.*,login_tb.* FROM user_tb INNER JOIN login_tb ON user_tb.lid_id=login_tb.lid")
        allv = cursor.fetchall()
        json_data = []
        for obj in allv:
            json_data.append(
                {"uid": obj[0], "lid": obj[1], "uname": obj[2],"ugender":obj[3],"uplace":obj[4],"homename":obj[5],"upin":obj[6],"upost":obj[7],"uemail":obj[8],"uphno":obj[9],"username":obj[11],"password":obj[12]})
        return JsonResponse(json_data, safe=False)

    def post(self,request):
        lid=request.data['lid']
        ob=LoginTb.objects.get(lid=lid)
        ob.username=request.data['username']
        ob.save()
        obj=UserTb.objects.get(lid_id=lid)
        obj.uname=request.data['uname']
        obj.ugender=request.data['ugender']
        obj.uplace=request.data['uplace']
        obj.homename=request.data['homename']
        obj.upost=request.data['upost']
        obj.upin=request.data['upin']
        obj.uemail=request.data['uemail']
        obj.uphno=request.data['uphno']
        obj.save()
        return HttpResponse("OK")
# Create your views here.
