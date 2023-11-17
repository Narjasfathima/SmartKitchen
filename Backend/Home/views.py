from django.shortcuts import render
from Home.models import LoginTb
from Shop.models import ShopTb

from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Home.serializer import Login_serializer
# Create your views here.

def home(request):
    return render(request,'Home/home.html')

def a_login(request):
    if request.method == "POST":
        uname = request.POST.get('uname')
        pwd = request.POST.get('pwd')
        obj = LoginTb.objects.filter(username=uname, password=pwd)

        for ob in obj:
            type = ob.ltype
            lid = ob.lid

            if type == "admin":
                return render(request, 'Home/a_home.html')
    return render(request,'Home/a_index.html')

def a_home(request):
    return render(request,'Home/a_home.html')


def s_home(request):
    return render(request,'Home/s_home.html')

def s_login(request):
    if request.method=="POST":
        uname=request.POST.get('uname')
        pwd=request.POST.get('pwd')
        obj=LoginTb.objects.filter(username=uname,password=pwd)

        for ob in obj:
            type=ob.ltype
            lid = ob.lid

        obj1 = ShopTb.objects.filter(lid_id=lid)
        for ob1 in obj1:
            sid = ob1.sid
            status = ob1.status

            if type=="shop" and status=='accept':
                request.session["lid"] = lid
                request.session["sid"]=sid
                return render(request, 'Home/s_home.html')

    return render(request,'Home/s_index.html')


class Login_view(APIView):
    def get(self, request):
        s = LoginTb.objects.all()
        ser =  Login_serializer(s, many=True)
        return Response(ser.data)

    def post(self, request):
        username = request.data['username']
        password = request.data['password']
        # ltype=request.data['ltype']
        obj = LoginTb.objects.filter(username=username, password=password)
        ser = Login_serializer(obj, many=True)
        return Response(ser.data)


