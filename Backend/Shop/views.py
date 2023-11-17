from django.core.mail import send_mail
from django.shortcuts import render
from django.http import HttpResponse
from Shop.models import ShopTb
from Home.models import LoginTb


from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Home.serializer import Login_serializer
from Shop.serializer import Shop_serializer


def shop_reg(request):
    if request.method=="POST":
        sname=request.POST.get('sname')
        place=request.POST.get('place')
        phone=request.POST.get('phone')
        email=request.POST.get('email')
        uname=request.POST.get('uname')
        pwd=request.POST.get('pwd')
        confpwd=request.POST.get('confpwd')
        if pwd==confpwd:
            obj=ShopTb()
            obj.sname=sname
            obj.splace=place
            obj.sphno=phone
            obj.semail=email
            obj.status='waiting'
            obj1=LoginTb()
            obj1.username=uname
            obj1.password=pwd
            obj1.ltype='shop'
            obj1.save()
            obj.lid_id=obj1.lid
            obj.save()
            return render(request, 'Shop/s_reg.html')
        else:
            obj="Password and Confirm password is missmatch!!!"
            context={
                'message':obj,
            }
            return render(request, 'Shop/s_reg.html',context)

    return render(request,'Shop/s_reg.html')

def view_shop(request):
    obj = ShopTb.objects.all()
    context = {
        'objval': obj,
    }
    return render(request,'Shop/View_shop.html',context)

def verify_shop(request):
    obj=ShopTb.objects.filter(status='waiting')
    context={
        'objval':obj,
    }

    return render(request,'Shop/Verif_shop.html',context)

def confirm_shop(request,shop_id):
    obj=ShopTb.objects.get(sid=shop_id)
    obj.status='accept'
    obj.save()
    uname=obj.lid.username
    pwd=obj.lid.password
    email = obj.semail
    send_mail('SMART KITCHEN',
              'Your shop registration is accepted.'
              ' Username :' + uname + ' and password: ' + pwd,
              'smartkitchen80@gmail.com',
              [email],
              fail_silently=False)
    return verify_shop(request)

def cancel_shop(request,shop_id):
    obj=ShopTb.objects.get(sid=shop_id)
    obj.status='reject'
    obj.save()
    email = obj.semail
    send_mail('SMART KITCHEN',
              'Your shop registration is rejected.....',
              'smartkitchen80@gmail.com',
              [email],
              fail_silently=False)
    return verify_shop(request)

def edit_shop(request):
    sid=str(request.session['sid'])
    lid=str(request.session['lid'])
    if request.method=="POST":
        obj=ShopTb.objects.get(sid=sid)
        obj1=LoginTb.objects.get(lid=lid)
        pwd = request.POST.get('pwd')
        if obj1.password==pwd:
            obj.sname=request.POST.get('sname')
            obj.splace=request.POST.get('place')
            obj.sphno=request.POST.get('phone')
            obj.semail=request.POST.get('email')
            obj1.username=request.POST.get('uname')
            obj.save()
            obj1.save()
            return render(request,'Home/s_home.html')
        else:
            obj = "Invalid password!!!"
            context = {
                'message': obj,
            }
            return render(request,'Shop/Edit_shop.html',context)

    sid=str(request.session['sid'])
    print(sid)
    obj1=ShopTb.objects.get(sid=sid)
    context={
        'objval':obj1,
    }
    return render(request,'Shop/Edit_shop.html',context)

def change_pwd(request):
    if request.method=="POST":
        pwd=request.POST.get('pwd')
        lid=str(request.session['lid'])
        obj=LoginTb.objects.get(lid=lid)
        if pwd==obj.password:
            npwd=request.POST.get('npwd')
            confpwd=request.POST.get('confpwd')
            if npwd==confpwd:
                obj.password=npwd
                obj.save()
                obj1 = "Password is changed!!!.Please check your email...."
                context = {
                    'message': obj1,
                }
                obj2=ShopTb.objects.get(lid_id=lid)
                uname = obj2.lid.username
                pwd = obj2.lid.password
                email = obj2.semail
                send_mail('SMART KITCHEN',
                          'Your password is changed.'
                          ' Username :' + uname + ' and password: ' + pwd,
                          'smartkitchen80@gmail.com',
                          [email],
                          fail_silently=False)
                return render(request, 'Shop/Edit_shop.html', context)
            else:
                obj = "New password and confirm password is missmatch!!!"
                context = {
                    'message': obj,
                }
                return render(request, 'Shop/Change_pwd.html', context)
        else:
            obj = "Current password is invalid!!!"
            context = {
                'message': obj,
            }
            return render(request, 'Shop/Change_pwd.html', context)

    return render(request,'Shop/Change_pwd.html')


def forget_pwd(request):
    if request.method=="POST":
        uname=request.POST.get('uname')
        if LoginTb.objects.filter(username=uname).exists():
            obj=LoginTb.objects.get(username=uname)
            pwd=obj.password
            lid=obj.lid
            print(lid)
            ob=ShopTb.objects.get(lid_id=lid)
            email=ob.semail
            print (email)
            send_mail('SMART KITCHEN',
                      'Your Forgotten Username :'+uname+ ' and password: '+pwd,
                      'smartkitchen80@gmail.com',
                      [email],
                      fail_silently=False)
    return render(request,'Shop/Forget_pwd.html')
# Create your views here.

class Shop_view(APIView):
    def get(self,request):
        obj=ShopTb.objects.all()
        ser=Shop_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=ShopTb()
        obj.lid_id=request.data['lid']
        obj.sname=request.data['sname']
        obj.splace=request.data['splace']
        obj.sphno=request.data['sphno']
        obj.semail=request.data['semail']
        obj.status=request.data['status']
        obj.save()
        return HttpResponse("OK")


