from django.conf.urls import url
from Home import views


urlpatterns = [
    # url(r'^$',views.home),
    url(r'^a_index/',views.a_login),
    url(r'^a_home/',views.a_home),
    url(r'^s_index/',views.s_login),
    url(r'^s_home/',views.s_home),

    url(r'^android/', views.Login_view.as_view()),

    # url(r'^req/',views.req),
    # url(r'^mng', views.viewnurs),
    # url(r'^deluser/(?P<nuid>\w+)', views.deluser, name="deluser1"),

]