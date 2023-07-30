<template>
  <div id="app" class="container mt-3">
  <h1>金融商品喜好紀錄系統</h1>
    <div>
      <h2>新增金融商品</h2>
      <form @submit.prevent="addProduct">
        <label for="productName">產品名稱　</label>
        <input type="text" v-model="newProduct.productName"  required>
        <br><br>
        <label for="productPrice">產品價格　</label>
        <input type="text" v-model="newProduct.productPrice" required>
        <br><br>
        <label for="feeRate">手續費率　</label>
        <input type="text" v-model="newProduct.feeRate" required>
        <br><br>
        <label for="account">扣款的帳號　</label>
        <input type="text" v-model="newProduct.account" required>
        <br><br>
        <label for="quantity">購買數量　</label>
        <input type="text" v-model="newProduct.quantity" required>
        <br><br>
        <label for="quantity">電子郵件信箱　</label>
        <input type="text" v-model="newProduct.email" required>
        <br><br>
        <button type="submit" class="btn btn-primary">新增</button>
      </form>
    </div>
    <h2>查詢喜好金融商品清單</h2>
    <table class="table mt-4">
      <thead>
        <tr>     
          <th width="10%">產品名稱</th>
          <th width="10%">產品價格</th>
          <th width="10%">手續費率</th>
          <th width="10%">扣款總金額</th>
          <th width="10%">總手續費用</th>
          <th width="10%">扣款帳號</th>
          <th width="10%">使用者聯絡電子信箱</th>
          <th width="10%">操作</th>     
        </tr> 
        </thead>    
        <tbody>
            <tr v-for="product in products" :key="product.productId">
              <td>{{ product.productName }}</td>
              <td>{{ product.productPrice }}</td>
              <td>{{ product.feeRate }}</td>
              <td>{{ product.productPrice * product.quantity }}</td>
              <td>{{ product.feeRate * product.productPrice * product.quantity }}</td>
              <td>{{ product.account }}</td>
              <td>{{ product.email }}</td>
              <td>
                <button @click="deleteProduct(product.productId)" >刪除</button>
                <button @click="openEditModal(product)">編輯</button>
              </td>
            </tr>
        </tbody>
        <div v-if="showModal" class="modal">
            <div class="modal-content">
              <h3>編輯商品</h3>
                <form @submit.prevent="updateProduct" >
                  <label for="productName">產品名稱　</label>
                  <input type="text" v-model="editedProduct.productName" required>
                  <br><br>
                  <label for="productPrice">產品價格　</label>
                  <input type="text" v-model="editedProduct.productPrice" required>
                  <br><br>
                  <label for="feeRate">手續費率　</label>
                  <input type="text" v-model="editedProduct.feeRate" required>
                  <br><br>
                  <label for="account">扣款的帳號　</label>
                  <input type="text" v-model="editedProduct.account" required>
                  <br><br>
                  <label for="quantity">購買數量　</label>
                  <input type="text" v-model="editedProduct.quantity" required>
                  <br><br>
                  <label for="quantity">電子郵件信箱　</label>
                  <input type="text" v-model="editedProduct.email" required>
                  <br><br>
                <button type="submit">確定</button>
                <button @click="showModal = false">取消</button>
                </form>
            </div>
        </div>
      </table>
  </div>
</template>

<script>
export default{
  data() {
    return{
      products: [],
      newProduct: {
        productName: '',
        productPrice: 0,
        feeRate: 0,
        account: '',
        quantity: 0,
        email: '',
      },
      showModal: false,
      editedProduct: null,
    }
  },
  methods: {
    addProduct() {
      if (this.newProduct.productPrice <= 0 || this.newProduct.quantity <= 0) {
        alert('產品價格和購買數量必須大於 0');
        return;
      }
      this.products.push(this.newProduct);
      this.newProduct = {
          productName: '',
          productPrice: 0,
          feeRate: 0,
          account: '',
          quantity: 0,
      };
      fetch('/api/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.newProduct),
      })
      .then(response => response.json())
      .catch(error => {
        console.error('新增商品時發生錯誤:', error);
      });
    },

    deleteProduct(productId) {
      this.products = this.products.filter(product => product.productId !== productId);
      fetch(`/api/products/${productId}`, {
        method: 'DELETE',
      })
      .then(response => {
        if (response.status === 204) {
          this.products = this.products.filter(product => product.productId !== productId);
        } 
        else {
          console.error('刪除商品時發生錯誤');
        }
      })
      .catch(error => {
        console.error('刪除商品時發生錯誤:', error);
      });
    },

    editProduct(product) {
      this.editProductFormVisible = true;
      this.showModal = true;
      this.editedProduct = { ...product };
    }
    ,
    openEditModal(product) {
      console.log(product);
      this.showModal = true;
      this.editedProduct = { ...product };
      
    },
    updateProduct() {
      const index = this.products.findIndex(product => product.productId === this.editedProduct.productId);
      if (index !== -1) {
        this.products[index] = { ...this.editedProduct };
      }
      this.showModal = false;
    },
  }
}
</script>
<style>

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 30; /* 設置較高的 z-index 值，確保視窗在背景之上 */
}

.modal-content {
  background-color: rgb(166, 202, 226);
  border: 5px rgb(0, 0, 0) ridge;
  padding: 10px;
  max-width: 400px;
  margin: 0 auto;
}

</style>