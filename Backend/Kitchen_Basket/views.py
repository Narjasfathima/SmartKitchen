from django.shortcuts import render
from Kitchen_Basket.models import KitchenBasketTb
from Kitchen_Basket.models import IngredientTb
from Kitchen_Basket.models import Recipie
from django.http import HttpResponse,JsonResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from Kitchen_Basket.serializer import KitchenBasket_serializer
from Kitchen_Basket.serializer import Ingredient_serializer
from .serializer import Temp_serializer
# Create your views here.
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from Kitchen_Basket.models import Temp
from django.db.models import Q

def add_kb(request):
    obj = IngredientTb.objects.all()
    context = {
        'obj':obj,
    }
    if request.method == "POST":
        re = request.session['re']
        f = request.session['f']
        print(f)
        print(re)
        ing = request.POST.get('incid')
        print(ing)
        s = ing.split()
        print(s)
        s.sort()
        sp=""
        for o in s:
            sp+=" "+o
        print(sp)

        obj = KitchenBasketTb()
        obj.name = f
        obj.recipie = re
        obj.inid = sp
        obj.save()
    return render(request,'Kitchen_Basket/Add_kitchen_basket.html',context)

def view_ingr(requst):
    obj = IngredientTb.objects.all()
    context = {
        'objval': obj,
    }
    return render(requst,'Kitchen_Basket/View_ingr.html',context)


def view_kb(request):
    obj=KitchenBasketTb.objects.all()
    for ob in obj:
        ingredient = ""
        ing = ob.inid.split()
        for i in ing:
            obj1=IngredientTb.objects.filter(inid=i)
            for o in obj1:
                ingredient = ingredient + o.ingred +", "

        ob.ingredients=ingredient
        ob.save()
        # ingredient_list.append(ingredient)
        # print(ingredient_list)
    context={
        'objval':obj,
    }
    print(context)
    return render(request, 'Kitchen_Basket/View_kitchen_basket.html', context)
    # context
    # return render(request,'Kitchen_Basket/View_kitchen_basket.html',context)


def delete_kb(request,k_id):
    obj=KitchenBasketTb.objects.get(kid=k_id)
    obj.delete()
    return view_kb(request)

def delete_ingr(request,in_id):
    obj=IngredientTb.objects.get(inid=in_id)
    obj.delete()
    return view_ingr(request)

def add_ingr(request):
    if request.method=="POST":
        obj=IngredientTb()
        obj.ingred=request.POST.get('ingr')
        obj.save()
        return view_ingr(request)
    return render(request,'Kitchen_Basket/Add_ingr.html')


def vali_uname(request):
    un=request.GET.get('uname')
    print(un)
    request.session['f']=un
    valid=un
    context={
        "is_ok": valid,
    }
    return JsonResponse(context)

def vali_uname2(request):
    re=request.GET.get('uname')
    print(re)
    request.session['re']=re
    valid=re
    context={
        "is_ok": valid,
    }
    return JsonResponse(context)

def sentsimilar(request):
    X = "1 2 3 4"
    Y = "1 2 3 "
    X_list = word_tokenize(X)
    Y_list = word_tokenize(Y)
    l1 = []
    l2 = []
    sw = stopwords.words('english')
    # remove stop words from the string
    X_set = {w for w in X_list if not w in sw}
    Y_set = {w for w in Y_list if not w in sw}
    rvector = X_set.union(Y_set)
    for w in rvector:
        if w in X_set:
            l1.append(1)  # create a vector
        else:
            l1.append(0)
        if w in Y_set:
            l2.append(1)
        else:
            l2.append(0)
    c = 0

    # cosine formula
    for i in range(len(rvector)):
        c += l1[i] * l2[i]
    cosine = c / float((sum(l1) * sum(l2)) ** 0.5)
    print("similarity: ", cosine)
    print(cosine)
    return HttpResponse(cosine)


class KichenBasket_view(APIView):
    def get(self,request):
        obj=KitchenBasketTb.objects.all()
        ser=KitchenBasket_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=KitchenBasketTb()
        obj.name=request.data['name']
        obj.recipie=request.data['recipie']
        obj.inid=request.data['inid']
        obj.save()
        return HttpResponse("OK")

class Ingredient_view(APIView):
    def get(self,request):
        obj=IngredientTb.objects.all()
        ser=Ingredient_serializer(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=IngredientTb()
        obj.ingred=request.data['ingred']
        obj.save()
        return HttpResponse("OK")



class Technical(APIView):

    def get(self,request):
        obj=Temp.objects.all()
        ser=Temp_serializer(obj,many=True)
        return Response(ser.data)


    def post(self,request):
        ing1 = request.data['in1']
        ing2 = request.data['in2']
        ing3 = request.data['in3']
        ing4 = request.data['in4']
        ing5 = request.data['in5']
        print(ing1)

        ing = ing1+" "+ing2+ " "+ing3+" "+ing4+ " " +ing5
        print(ing)

        ob = KitchenBasketTb.objects.filter(Q(inid__icontains=ing1) | Q(inid__icontains=ing2)| Q(inid__icontains=ing3)| Q(inid__icontains=ing4)| Q(inid__icontains=ing5))
        print(ob.count())

        o = Temp.objects.all()
        o.delete()


        flist=[]
        for r in ob:
            print(r.name +" " + r.inid)
            tinc=r.inid.split(' ')
            # minc=ing.split(' ')
            a=0
            for inc in tinc:
                if inc in ing:
                    a=0
                else:
                    a=1
                    break
            if a==0:
                flist.append(r.name+ " " +r.inid)
                obj = Temp()
                obj.inid = r.inid
                obj.name = r.name
                obj.rec=r.recipie
                obj.save()
        print(flist)



        return HttpResponse("OK")