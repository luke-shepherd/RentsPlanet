// Vue element handling just the search bar
var searchinfo = new Vue({
    el: '#searchinfo',
    data: {
        address: 'address:empty',
        searched: false,
        addrinfo: [
            'insert',
            'scraping data',
            'here'
        ]
    },

    methods: {
        handleSearch: function(){
            //logFunctionCall();
            searchinfo.searched = true;
            reviews.searched = true;
            createform.searched = true;
            get_housing_data(searchinfo.address);

            // assumes one review is sent
            window.setTimeout( function() {
                var response = xmlHtt.response;
                //log(response);
                var obj = JSON.parse(response);
                //log(obj);
                if (obj.address != null) {
                    reviews.createUpdate(obj);
                }
            }, 1000);
        },

        cancelSearch: function() {
            //logFunctionCall();
            window.setTimeout( window.onresize, 200);
            document.getElementById('pac-input').value = '';
            searchinfo.searched = false;
            reviews.searched = false;
            createform.searched = false;
            reviews.reviews=[];
        }
    }
});


// Vue element handling the list of shown reviews
var reviews = new Vue({
    el: '#reviews',
    data: {
        // currently contains test data; should start empty
        reviews: [],

        // edit data of currently chosen review
        edit_form: {
            landlord:'',
            review:'',
            creator:'',
            email:'',
        },
        // index of review being edited
        num_edit:-1,
        searched: searchinfo.searched,
    },

    methods: {
        // delete data from server and page
        handleDelete: function(idx) {
            //logFunctionCall();
            get_rid_of(this.reviews[idx]);
            this.reviews.splice(idx, 1);
            this.toggleEdit(idx);
        },

        // handler method to insert a review object 
        //     to the beginning of the list of reviews
        createUpdate: function(arr_el) {
            this.reviews.unshift(arr_el);
        },

        // shows a reset form to edit
        // slightly wasteful in that it always resets each field
        showEdit: function(idx) {
            var review = this.reviews[idx];
            this.edit_form.landlord = review.landlord;
            this.edit_form.review = review.review;
            this.edit_form.creator = review.creator;
            this.edit_form.email = review.email;
            this.edit_form.rent = review.rent;
            this.toggleEdit(idx);
        },

        // toggles edit for only one of the listed reviews
        // only one review may be editted at a time
        toggleEdit: function(idx) {
            if (this.num_edit == idx)
                this.num_edit = -1;
            else
                this.num_edit = idx;
        },

        // send newly edited data to server
        // update review locally too
        handleEdit: function(idx) {
            var review = this.reviews[idx];
            review.landlord = this.edit_form.landlord;
            review.review = this.edit_form.review;
            review.creator = this.edit_form.creator;
            review.email = this.edit_form.email;
            review.rent = this.edit_form.rent;
            // TODO send server edit data
            this.toggleEdit(idx);
        }
    },
});

// Vue element handling review creation
var createform = new Vue({
    el: '#createform',
    data: {
        form: false,
        landlord: '',
        review: '',
        creator: 'tenant',
        email: '',
        rent: '',
        searched: searchinfo.searched,
    },

    methods: {
        // show the form to add reviews 
        showForm: function() {
            this.form = true;
        },

        // send form data to server
        // update page by 'reviews' vue element
        handleCreate: function() {
            //logFunctionCall();
            var obj = {
                landlord: this.landlord,
                review: this.review,
                creator: this.creator,
                email: this.email,
                rent: this.rent,
                address: searchinfo.address
            };
            reviews.createUpdate(obj);
            submit_review(obj);
            this.cancelForm();
        },

        // resets the form
        cancelForm: function() {
            //logFunctionCall();
            this.form = false;
            this.landlord = '';
            this.review = '';
            this.creator = 'tenant';
            this.email = '';
            this.rent = '';
        }
    }
});

// LOGGING FUNCTIONS -----------------
function log(thing) {
    console.log(' ' + thing);
}

function logFunctionCall(){
    console.log(arguments.callee.caller.name);
}
