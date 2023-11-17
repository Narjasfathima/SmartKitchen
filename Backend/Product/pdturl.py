from django.conf.urls import url
from Product import views


urlpatterns = [
    url(r'^add_pdt/',views.add_pdt),
    url(r'^view_pdt/',views.view_pdt),
    url(r'^edit_pdt/(?P<pdt_id>\w+)', views.edit_pdt, name="editproduct"),
    url(r'^dltpdt/(?P<pdt_id>\w+)', views.delete_pdt, name="dltpdt"),

    url(r'^android/', views.Product_view.as_view()),

]