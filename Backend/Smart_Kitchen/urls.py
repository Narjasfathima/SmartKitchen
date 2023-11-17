"""Smart_Kitchen URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
import Home.views as view

from django.conf.urls import url,include
from django.contrib.staticfiles.urls import staticfiles_urlpatterns


urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^$',view.home),
    url(r'^home1/',view.home),
    url(r'^home/',include('Home.homeurl')),
    url(r'^kitchen_b/', include('Kitchen_Basket.kburl')),
    url(r'^meal_c/', include('Meal_Calender.mcurl')),
    url(r'^order/', include('Order_Manage.orderurl')),
    url(r'^product/', include('Product.pdturl')),
    url(r'^shop/', include('Shop.shurl')),
    url(r'^user/', include('User.usurl')),
    url(r'^feed/', include('Feedback.feedurl')),
    url(r'^comment/',include('Comment.comurl')),
    # url(r'^temp/', include('temp.url')),
]
