import type { Meta } from '@storybook/react';

export default {
  title: 'Design system/2.Color',
  argTypes: {
    backgroundColor: { control: 'color' },
  },
} as Meta;

const Circle = (bgColor: string) => {
  const css = {
    width: '100px',
    height: '100px',
    borderRadius: '50%',
    marginLeft: '5px',
    marginRight: '5px',
    backgroundColor: `var(${bgColor})`,
  };
  return <div style={css} />;
};

const GrayColor = () => {
  const colors = [
    Circle('--gray-darker'),
    Circle('--gray-dark'),
    Circle('--gray'),
    Circle('--gray-light'),
    Circle('--gray-lighter'),
  ];

  const css = {
    display: 'flex',
    flexDirection: 'row' as const,
    width: '100%',
  };
  return <div style={css}>{colors}</div>;
};

const PrimaryColor = () => {
  const colors = [
    Circle('--color-primary-darker'),
    Circle('--color-primary-dark'),
    Circle('--color-primary'),
    Circle('--color-primary-light'),
    Circle('--color-primary-lighter'),
  ];

  const css = {
    display: 'flex',
    flexDirection: 'row' as const,
    width: '100%',
  };
  return <div style={css}>{colors}</div>;
};

const AccentColor = () => {
  const colors = [
    Circle('--color-accent-darker'),
    Circle('--color-accent-dark'),
    Circle('--color-accent'),
    Circle('--color-accent-light'),
    Circle('--color-accent-lighter'),
  ];

  const css = {
    display: 'flex',
    flexDirection: 'row' as const,
    width: '100%',
  };
  return <div style={css}>{colors}</div>;
};

export const Basic = () => {
  return (
    <div>
      <h4>--gray</h4>
      {GrayColor()}
      <h4>--color-primary</h4>
      {PrimaryColor()}
      <br />
      <h4>--color-accent</h4>
      {AccentColor()}
    </div>
  );
};
