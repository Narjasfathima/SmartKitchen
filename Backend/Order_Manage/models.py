from django.db import models
from Shop.models import ShopTb
from Product.models import ProductTb
from User.models import UserTb

# Create your models here.
class OrderProductTb(models.Model):
    oid = models.AutoField(primary_key=True)
    # pid = models.IntegerField()
    pid= models.ForeignKey(ProductTb, to_field='pid', on_delete=models.CASCADE)
    oqty = models.CharField(max_length=25)
    oprice = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'order_product_tb'


class BookProductTb(models.Model):
    bid = models.AutoField(primary_key=True)
    # sid = models.IntegerField()
    sid= models.ForeignKey(ShopTb, to_field='sid', on_delete=models.CASCADE)
    # uid = models.IntegerField()
    uid= models.ForeignKey(UserTb, to_field='uid', on_delete=models.CASCADE)
    bdate = models.DateField()
    btime = models.TimeField()
    bstatus = models.CharField(max_length=25)
    # oid = models.IntegerField()
    oid= models.ForeignKey(OrderProductTb, to_field='oid', on_delete=models.CASCADE)

    class Meta:
        managed = False
        db_table = 'book_product_tb'
