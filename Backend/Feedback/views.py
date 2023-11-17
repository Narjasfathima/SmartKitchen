from django.shortcuts import render

from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Feedback.serializer import Feedback_serializer, Tech_serializer

from Feedback.models import FeedbackTb, TechTb
import datetime
from Home.models import LoginTb
# Create your views here.
def add_feed(request):
    if request.method=="POST":
        obj=FeedbackTb()
        obj.feed=request.POST.get('feed')
        e=datetime.datetime.now()
        obj.fdate=e.date()
        obj.ftime=e.time()
        obj.lid_id=str(request.session['lid'])
        obj.fstatus='no read'
        obj.save()
    return render(request,'Feedback/Add_feedback.html')

def view_feed(request):
    obj=FeedbackTb.objects.filter(fstatus='no read')
    context={
        'objval':obj,
    }
    return render(request,'Feedback/View_feedback.html',context)

def read_feed(request,feed_id):
    obj=FeedbackTb.objects.get(fid=feed_id)
    obj.fstatus='read'
    obj.save()
    return view_feed(request)

def delete_feed(request,feed_id):
    obj=FeedbackTb.objects.get(fid=feed_id)
    obj.delete()
    return view_feed(request)



class Feedback_view(APIView):
    def get(self,request):
        obj=FeedbackTb.objects.all()
        ser=Feedback_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=FeedbackTb()
        obj.lid_id=request.data['lid']
        obj.feed=request.data['feed']
        e = datetime.datetime.now()
        obj.fdate = e.date()
        obj.ftime = e.time()
        obj.fstatus ="no read"
        obj.save()
        return HttpResponse("OK")

import re

class Tech_view(APIView):
    def get(self,request):
        obj=TechTb.objects.all()
        ser=Tech_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        det = request.data['detail']
        print(det)
        hum = ""
        if 'Human' in det:
            hum="found"
        else:
            hum="no"
        print(hum)

        temp = re.findall(r'\d+', det)
        res = list(map(int, temp))
        print(res[0])
        r = res[0]
        temp = ""
        if r >= 40 :
            temp = "high"
        else:
            temp = "no"

        alarm = hum + "#" + temp
        print(alarm)
        return HttpResponse(alarm)

