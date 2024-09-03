document.addEventListener("DOMContentLoaded", function() {
  const fetchButton = document.getElementById('fetch-button');
  const addUserButton = document.getElementById('add-user-button');

  fetchButton.addEventListener('click', fetchUsers);
  addUserButton.addEventListener('click', addUser);
});

function fetchUsers() {
  fetch('http://localhost:8080/users')
    .then(response => response.json())
    .then(users => {
      const usersContainer = document.getElementById('users-container');
      usersContainer.innerHTML = ''; // Clear previous users
      users.forEach(user => {
        const userDiv = document.createElement('div');
        userDiv.classList.add('user');
        userDiv.innerHTML = `
          <h3>${user.id}. ${user.name}</h3>
          <p>Email: ${user.email}</p>
        `;
        usersContainer.appendChild(userDiv);
      });
    })
    .catch(error => console.error('Error fetching users:', error));
}

function addUser() {
  const nameInput = document.getElementById('name-input');
  const passInput = document.getElementById('pass-input');
  const emailInput = document.getElementById('email-input');

  const name = nameInput.value.trim();
  const email = emailInput.value.trim();
  const password = passInput.value.trim();

  if (name === '' || email === '' || password ==='') {
    alert('Please enter both name and email.');
    return;
  }

  const newUser = {
    name: name,
    password: password,
    email: email
  };

  fetch('http://localhost:8080/users', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(newUser)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Failed to add user');
    }
    nameInput.value = '';
    emailInput.value = '';
    fetchUsers(); // Refresh the user list after adding
  })
  .catch(error => console.error('Error adding user:', error));
}
