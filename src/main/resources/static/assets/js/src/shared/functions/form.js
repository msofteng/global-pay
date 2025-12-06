export default function formDataToJson(formData) {
  const obj = {};

  function convert(value) {
    if (value.trim() === "") {
      return undefined;
    }

    if (!isNaN(value)) {
      return Number(value);
    }

    return value;
  }

  for (const [key, value] of formData.entries()) {
    const converted = convert(value);

    if (converted === undefined) {
      continue;
    }

    const keys = key.split('.');
    let current = obj;

    keys.forEach((k, index) => {
      if (index === keys.length - 1) {
        current[k] = converted;
      } else {
        current[k] = current[k] || {};
        current = current[k];
      }
    });
  }

  return obj;
}