from django.conf.urls import url
from User import views


urlpatterns = [
    url(r'^android/', views.User_view.as_view()),
    url(r'^android1/', views.User_view1.as_view()),

    # url(r'^shop_reg/',views.shop_reg),
    # url(r'^edit_shop/',views.edit_shop),
    # url(r'^ver_shop', views.verify_shop),
    # # url(r'^view_shop', views.edit_pdt),
    # url(r'^deluser/(?P<nuid>\w+)', views.deluser, name="deluser1"),

]