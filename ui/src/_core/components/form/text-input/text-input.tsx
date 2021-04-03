import React, { useState } from 'react';
import './text-input.scss';

export interface TextInputProps {
  label?: string;
  name: string;
  value: string;

  onChange: (newValue: string) => void;
}

export const TextInput: React.FC<TextInputProps> = ({
  label,
  name,
  value,
  onChange,
}) => {
  const [inputValue, setInputValue] = useState(value);

  const handleChange = (event: React.FormEvent<HTMLInputElement>) => {
    let newValue = event.currentTarget.value;
    setInputValue(newValue);
    onChange(newValue);
  };

  const labelTag = label ? <legend className="label">{label}</legend> : null;
  return (
    <fieldset className="input-text">
      {labelTag}
      <input
        type="text"
        name={name}
        value={inputValue}
        onChange={handleChange}
      />
    </fieldset>
  );
};
