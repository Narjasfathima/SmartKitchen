from django.shortcuts import render
from Meal_Calender.models import McalenderTb

from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Meal_Calender.serializer import MealCalender_serializer

class Mcalender_view(APIView):
    def get(self,request):
        obj=McalenderTb.objects.all()
        ser=MealCalender_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=McalenderTb()
        obj.uid_id=request.data['uid']
        obj.mcdate=request.data['mcdate']
        obj.mctime=request.data['mctime']
        obj.mcbreak = request.data['mcbreak']
        obj.mclunch = request.data['mclunch']
        obj.mcdinner = request.data['mcdinner']
        obj.save()
        return HttpResponse("OK")

import datetime
from datetime import datetime



class Noti(APIView):
    def post(self,request):
        uid = request.data['uid']

        now = datetime.now()
        current_time = now.strftime("%H:%M:%S")
        print("Current Time =", current_time)

        dt = current_time[0:2]
        print(dt)

        obj=McalenderTb.objects.filter(uid_id=uid)
        t = ""
        res = ""
        for x in obj:
            t = x.mctime
            # print(t)

            tt = t[0:2]
            print(tt)
            if tt==dt:
                res = "ok"
            else:
                res = "no"
        print(res)
        return HttpResponse(res)

# Create your views here.
