
function login(context) {
  let usernameInput, passwordInput;

  if (context) {
    ({ usernameInput, passwordInput } = context);
  }

  if (!usernameInput && typeof document !== 'undefined') {
    usernameInput = document.getElementById('loginUsername');
    passwordInput = document.getElementById('loginPassword');
  }

  if (!usernameInput || !passwordInput) {
    return { ok: false, reason: 'MISSING_DOM' };
  }

  const username = String(usernameInput.value ?? '').trim();
  const password = String(passwordInput.value ?? '');


  if (isEmpty(username) || isEmpty(password)) {

    return { ok: false, reason: 'INVALID' };
  }


  console.log(`Login clicked. Username: ${username}, Password: ${password}`);

  return { ok: true, payload: { username, password } };
}

function isEmpty(value) {
  return String(value ?? '').trim().length === 0;
}

function isValidUsername(value) {
  // Letters, numbers, underscores only
  const re = /^[A-Za-z0-9_]+$/;
  return re.test(String(value));
}

function isValidEmail(value) {
  // Practical email pattern
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
  return re.test(String(value));
}

function isValidPassword(value) {
  return String(value).length >= 8;
}


function setError(inputEl, errorEl, message) {
  if (!inputEl || !errorEl) return;
  inputEl.setAttribute?.('aria-invalid', 'true');
  errorEl.textContent = message;
}

function clearError(inputEl, errorEl) {
  if (!inputEl || !errorEl) return;
  inputEl.setAttribute?.('aria-invalid', 'false');
  errorEl.textContent = '';
}

function register(context) {
  let nameInput, emailInput, usernameInput, passwordInput;
  let nameError, emailError, usernameError, passwordError, successMsg;

  if (context) {
    ({
      nameInput,
      emailInput,
      usernameInput,
      passwordInput,
      nameError,
      emailError,
      usernameError,
      passwordError,
      successMsg,
    } = context);
  }


  if (!nameInput && typeof document !== 'undefined') {
    nameInput = document.getElementById('registerName');
    emailInput = document.getElementById('registerEmail');
    usernameInput = document.getElementById('registerUsername');
    passwordInput = document.getElementById('registerPassword');

    nameError = document.getElementById('nameError');
    emailError = document.getElementById('emailError');
    usernameError = document.getElementById('usernameError');
    passwordError = document.getElementById('passwordError');

    successMsg = document.getElementById('successMsg');
  }


  if (!nameInput || !emailInput || !usernameInput || !passwordInput) {
    return { ok: false, reason: 'MISSING_DOM' };
  }

  if (successMsg) successMsg.style.display = 'none';
  let firstInvalid = null;


  if (isEmpty(nameInput.value)) {
    setError(nameInput, nameError, 'Name is required.');
    firstInvalid = firstInvalid || nameInput;
  } else {
    clearError(nameInput, nameError);
  }


  if (isEmpty(emailInput.value)) {
    setError(emailInput, emailError, 'Email is required.');
    firstInvalid = firstInvalid || emailInput;
  } else if (!isValidEmail(emailInput.value)) {
    setError(emailInput, emailError, 'Please enter a valid email address.');
    firstInvalid = firstInvalid || emailInput;
  } else {
    clearError(emailInput, emailError);
  }


  if (isEmpty(usernameInput.value)) {
    setError(usernameInput, usernameError, 'Username is required.');
    firstInvalid = firstInvalid || usernameInput;
  } else if (!isValidUsername(usernameInput.value)) {
    setError(
      usernameInput,
      usernameError,
      'Username can only contain letters, numbers, and underscores.'
    );
    firstInvalid = firstInvalid || usernameInput;
  } else {
    clearError(usernameInput, usernameError);
  }


  if (isEmpty(passwordInput.value)) {
    setError(passwordInput, passwordError, 'Password is required.');
    firstInvalid = firstInvalid || passwordInput;
  } else if (!isValidPassword(passwordInput.value)) {
    setError(passwordInput, passwordError, 'Password must be at least 8 characters long.');
    firstInvalid = firstInvalid || passwordInput;
  } else {
    clearError(passwordInput, passwordError);
  }

  if (firstInvalid) {
    firstInvalid.focus?.();
    return { ok: false, reason: 'INVALID' };
  }

  if (successMsg) successMsg.style.display = 'block';

  name = String(nameInput.value).trim();
  email = String(emailInput.value).trim();
  username = String(usernameInput.value).trim();
  password = String(passwordInput.value).trim();

  const payload = {
    name: String(nameInput.value).trim(),
    email: String(emailInput.value).trim(),
    username: String(usernameInput.value).trim(),
    password: passwordInput.value, 
  };



console.log(
  `Register clicked. Name: ${name}, Email: ${email}, Username: ${username}, Password: ${password}`
);

  return { ok: true, payload };
}


function init() {
  if (typeof document === 'undefined') return;

  const fields = [
    { input: 'registerName', error: 'nameError', validate: (v) => !isEmpty(v) },
    { input: 'registerEmail', error: 'emailError', validate: (v) => !isEmpty(v) && isValidEmail(v) },
    { input: 'registerUsername', error: 'usernameError', validate: (v) => !isEmpty(v) && isValidUsername(v) },
    { input: 'registerPassword', error: 'passwordError', validate: (v) => !isEmpty(v) && isValidPassword(v) },
  ];

  fields.forEach(({ input, error, validate }) => {
    const inputEl = document.getElementById(input);
    const errorEl = document.getElementById(error);
    if (!inputEl || !errorEl) return;

    inputEl.addEventListener('input', () => {
      const v = inputEl.value;
      if (validate(v)) {
        clearError(inputEl, errorEl);
      }
    });
  });
}


if (typeof document !== 'undefined') {
  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
  } else {
    init();
  }
}

if (typeof module !== 'undefined' && module.exports) {
  module.exports = {
    register,
    init,
    isEmpty,
    isValidUsername,
    isValidEmail,
    isValidPassword,
    setError,
    clearError,
    login
  };
} 