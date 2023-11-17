from django.conf.urls import url
from Order_Manage import views


urlpatterns = [
    url(r'^view_order/',views.view_order),
    url(r'^confirm_order/(?P<book_id>\w+)', views.confirm_order, name="conforder"),
    url(r'^cancel_order/(?P<book_id>\w+)', views.cancel_order, name="cancelorder"),

    url(r'^android/', views.Book_view.as_view()),
    url(r'^android1/', views.Order_view.as_view()),
    url(r'^android2/', views.Book_view1.as_view()),

    # url(r'^req/',views.req),
    # url(r'^mng', views.viewnurs),
    # url(r'^deluser/(?P<nuid>\w+)', views.deluser, name="deluser1"),

]