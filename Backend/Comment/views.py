import datetime

from django.shortcuts import render
from django.http import HttpResponse
from Comment.serializer import Comment_serializer
from Comment.serializer import CookVideo_serializer
from Comment.models import CommentTb
from Comment.models import CookVideoTb
from rest_framework.response import Response
from rest_framework.views import APIView
import string
from django.http import JsonResponse
from django.db import connection
from django.core.files.storage import FileSystemStorage
from Smart_Kitchen import settings
from PIL import Image
from io import BytesIO
import base64
# Create your views here.

class Comment_view(APIView):
    def get(self,request):
        obj=CommentTb.objects.all()
        ser=Comment_serializer(obj,many=True)
        return Response(ser.data)


    def post(self,request):
        obj=CommentTb()
        obj.uid_id=request.data['uid']
        obj.cid_id=request.data['cid']
        obj.com=request.data['com']
        obj.save()
        return HttpResponse("OK")

from django.views.decorators.csrf import csrf_exempt

class CookVideo_view(APIView):
    def get(self,request):
        obj=CookVideoTb.objects.all()
        ser=CookVideo_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=CookVideoTb()
        obj.uid_id=request.data['uid']
        obj.cname=request.data['cname']
        v = request.data['video']
        s=v.split("/")
        print(s)
        vv = s[-1]
        print(vv)
        obj.video=vv
        e = datetime.datetime.now()
        obj.cdate = e.date()
        obj.ctime = e.time()
        obj.save()
        #
        # idd = str(obj.cid)
        # i = idd + ".mp4"
        # f = request.data['video']
        # myfile = request.FILES['video']



        # ob = CookVideoTb.objects.get(cid=idd)
        # ob.video = i
        # ob.save()
        return HttpResponse("OK")

@csrf_exempt
def simple_upload(request):
    if request.method == 'POST' and request.FILES['uploadedfile']:
        myfile = request.FILES['uploadedfile']
        fs = FileSystemStorage()
        fpath = settings.BASE_DIR + settings.STATIC_URL +myfile.name
        print('hello')
        print(request.POST)

        fn=request.POST.get('')
        # filename = fs.save(myfile.name, myfile)
        filename = fs.save(fpath, myfile)

        return HttpResponse("uploaded")
    return HttpResponse("hello")
    #     uploaded_file_url = fs.url(filename)
    #     return render(request, 'core/simple_upload.html', {
    #         'uploaded_file_url': uploaded_file_url
    #     })
    # return render(request, 'core/simple_upload.html')



class video_view(APIView):
    def get(self,request):
        cursor = connection.cursor()
        cursor.execute("SELECT cook_video_tb.*,user_tb.uname FROM cook_video_tb INNER JOIN user_tb ON cook_video_tb.uid_id=user_tb.lid_id")
        allv = cursor.fetchall()
        json_data = []
        for obj in allv:
            json_data.append(
                {"cid": obj[0], "uid": obj[1], "cname": obj[2],"video":obj[3],"cdate":obj[4],"ctime":obj[5],"uname":obj[6]})
        return JsonResponse(json_data, safe=False)

class comment_view(APIView):
    def get(self,request):
        cursor = connection.cursor()
        cursor.execute("SELECT comment_tb.*,user_tb.uname FROM comment_tb INNER JOIN user_tb ON comment_tb.uid_id=user_tb.lid_id")
        allv = cursor.fetchall()
        json_data = []
        for obj in allv:
            json_data.append(
                {"comid": obj[0], "uid": obj[1], "cid": obj[2],"com":obj[3],"uname":obj[4]})
        return JsonResponse(json_data, safe=False)