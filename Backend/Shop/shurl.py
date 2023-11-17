from django.conf.urls import url
from Shop import views


urlpatterns = [
    url(r'^shop_reg/',views.shop_reg),
    url(r'^edit_shop/',views.edit_shop),
    url(r'^ver_shop/', views.verify_shop),
    url(r'^view_shop/', views.view_shop),
    url(r'^confirm/(?P<shop_id>\w+)', views.confirm_shop, name="confshop"),
    url(r'^cancel/(?P<shop_id>\w+)', views.cancel_shop, name="cancelshop"),
    url(r'^change_pwd/',views.change_pwd),
    url(r'^forget_pwd/', views.forget_pwd ),

    url(r'^android/', views.Shop_view.as_view()),

]