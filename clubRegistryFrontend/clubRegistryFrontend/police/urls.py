from django.urls import path
from .views import *

app_name='police'

urlpatterns = [
    path('shops', ShopsView.as_view(), name='shops'),
    path('shops/<int:id>', ActivateShopView, name='activate_shop')
]