from django.conf.urls import url
from Kitchen_Basket import views


urlpatterns = [
    url(r'^add_kb/',views.add_kb),
    url(r'^view_kb/',views.view_kb),
    url(r'^view_ingr/', views.view_ingr),
    url(r'^add_ingr/', views.add_ingr),
    url(r'^dlt_ingr/(?P<in_id>\w+)', views.delete_ingr, name="dltingr"),
    url(r'^dlt_kb/(?P<k_id>\w+)', views.delete_kb, name="dltkb"),
    url(r'^android/', views.KichenBasket_view.as_view()),
    url(r'^android1/', views.Ingredient_view.as_view()),
    url(r'^sim/', views.sentsimilar),
    url(r'valid2/', views.vali_uname2, name="vali_uname2"),
    url(r'valid/', views.vali_uname, name="vali_uname"),
    url(r'^tech/', views.Technical.as_view()),
    # url(r'^req/',views.req),
    # url(r'^mng', views.viewnurs),
    # url(r'^deluser/(?P<nuid>\w+)', views.deluser, name="deluser1"),

]