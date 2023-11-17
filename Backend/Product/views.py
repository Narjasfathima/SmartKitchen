from django.shortcuts import render
from django.core.files.storage import FileSystemStorage
from Product.models import ProductTb
from Shop.models import ShopTb

from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Product.serializer import Product_serializer


def add_pdt(request):
    if request.method=="POST":
        obj=ProductTb()
        obj.pname=request.POST.get('pname')
        obj.ptype=request.POST.get('ptype')
        obj.pqty=request.POST.get('qty')
        obj.price=request.POST.get('price')
        obj.sid_id=str(request.session['sid'])

        myfile = request.FILES["file"]
        fs = FileSystemStorage()
        filename = fs.save(myfile.name, myfile)
        obj.pimage = myfile.name
        obj.save()

        obj = "Product Added!!!"
        context = {
            'message': obj,
        }
        return render(request, 'Product/Add_product.html',context)
    return render(request,'Product/Add_product.html')

def view_pdt(request):
    obj=ProductTb.objects.all()
    context={
        'objval':obj,
    }
    return render(request,'Product/View_product.html',context)

def edit_pdt(request,pdt_id):
    if request.method=="POST":
        obj1=ProductTb.objects.get(pid=pdt_id)
        obj1.pname=request.POST.get('pname')
        obj1.ptype=request.POST.get('ptype')
        obj1.pqty=request.POST.get('qty')
        obj1.price=request.POST.get('price')

        myfile = request.FILES["file"]
        fs = FileSystemStorage()
        filename = fs.save(myfile.name, myfile)
        obj1.pimage = myfile.name
        obj1.save()
        return view_pdt(request)

    obj=ProductTb.objects.get(pid=pdt_id)
    context={
        'objval':obj,
    }
    return render(request,'Product/Edit_product.html',context)

def delete_pdt(request,pdt_id):
    obj=ProductTb.objects.get(pid=pdt_id)
    obj.delete()
    return view_pdt(request)
# Create your views here.

class Product_view(APIView):
    def get(self,request):
        obj=ProductTb.objects.all()
        ser=Product_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=ProductTb()
        obj.sid_id=request.data['sid']
        obj.pname=request.data['pname']
        obj.ptype=request.data['ptype']
        obj.pimage=request.data['pimage']
        obj.pqty=request.data['pqty']
        obj.price=request.data['price']
        obj.save()
        return HttpResponse("OK")